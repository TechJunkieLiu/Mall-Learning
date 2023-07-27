package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.enter.mapper.PmsProductMapper;
import com.aiyangniu.mall.enter.model.bo.MemberProductCollection;
import com.aiyangniu.mall.enter.model.pojo.PmsProduct;
import com.aiyangniu.mall.enter.model.pojo.UmsMember;
import com.aiyangniu.mall.enter.repository.MemberCollectionRepository;
import com.aiyangniu.mall.enter.service.MemberCollectionService;
import com.aiyangniu.mall.enter.service.UmsMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 会员商品收藏管理实现类
 *
 * @author lzq
 * @date 2023/07/05
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MemberCollectionServiceImpl implements MemberCollectionService {

    @Value("${mongo.insert.sqlEnable}")
    private Boolean sqlEnable;

    private final PmsProductMapper productMapper;
    private final MemberCollectionRepository memberCollectionRepository;
    private final UmsMemberService memberService;

    @Override
    public int add(MemberProductCollection productCollection) {
        int count = 0;
        if (productCollection.getProductId() == null) {
            return 0;
        }
        UmsMember member = memberService.getCurrentMember();
        productCollection.setMemberId(member.getId());
        productCollection.setMemberNickname(member.getNickname());
        productCollection.setMemberIcon(member.getIcon());
        MemberProductCollection findCollection = memberCollectionRepository.findByMemberIdAndProductId(productCollection.getMemberId(), productCollection.getProductId());
        if (findCollection == null) {
            if (sqlEnable) {
                PmsProduct product = productMapper.selectById(productCollection.getProductId());
                if (product == null || product.getDeleteStatus() == 1) {
                    return 0;
                }
                productCollection.setProductName(product.getName());
                productCollection.setProductSubTitle(product.getSubTitle());
                productCollection.setProductPrice(product.getPrice() + "");
                productCollection.setProductPic(product.getPic());
            }
            memberCollectionRepository.save(productCollection);
            count = 1;
        }
        return count;
    }

    @Override
    public int delete(Long productId) {
        UmsMember member = memberService.getCurrentMember();
        return memberCollectionRepository.deleteByMemberIdAndProductId(member.getId(), productId);
    }

    @Override
    public Page<MemberProductCollection> list(Integer pageNum, Integer pageSize) {
        UmsMember member = memberService.getCurrentMember();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return memberCollectionRepository.findByMemberId(member.getId(), pageable);
    }

    @Override
    public MemberProductCollection detail(Long productId) {
        UmsMember member = memberService.getCurrentMember();
        return memberCollectionRepository.findByMemberIdAndProductId(member.getId(), productId);
    }

    @Override
    public void clear() {
        UmsMember member = memberService.getCurrentMember();
        memberCollectionRepository.deleteAllByMemberId(member.getId());
    }
}
