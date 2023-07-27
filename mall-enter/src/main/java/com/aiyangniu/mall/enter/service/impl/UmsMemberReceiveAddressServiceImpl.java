package com.aiyangniu.mall.enter.service.impl;

import com.aiyangniu.mall.enter.mapper.UmsMemberReceiveAddressMapper;
import com.aiyangniu.mall.enter.model.pojo.UmsMember;
import com.aiyangniu.mall.enter.model.pojo.UmsMemberReceiveAddress;
import com.aiyangniu.mall.enter.service.UmsMemberReceiveAddressService;
import com.aiyangniu.mall.enter.service.UmsMemberService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 会员收货地址管理实现类
 *
 * @author lzq
 * @date 2023/05/24
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UmsMemberReceiveAddressServiceImpl extends ServiceImpl<UmsMemberReceiveAddressMapper, UmsMemberReceiveAddress> implements UmsMemberReceiveAddressService {

    private final UmsMemberService umsMemberService;
    private final UmsMemberReceiveAddressMapper addressMapper;

    @Override
    public List<UmsMemberReceiveAddress> list() {
        UmsMember currentMember = umsMemberService.getCurrentMember();
        return list(new LambdaQueryWrapper<UmsMemberReceiveAddress>().eq(UmsMemberReceiveAddress::getMemberId, currentMember.getId()));
    }

    @Override
    public UmsMemberReceiveAddress getItem(Long id) {
        UmsMember currentMember = umsMemberService.getCurrentMember();
        List<UmsMemberReceiveAddress> addressList = list(new LambdaQueryWrapper<UmsMemberReceiveAddress>().eq(UmsMemberReceiveAddress::getMemberId, currentMember.getId()).eq(UmsMemberReceiveAddress::getId, id));
        if(!CollectionUtils.isEmpty(addressList)){
            return addressList.get(0);
        }
        return null;
    }

    @Override
    public int add(UmsMemberReceiveAddress address) {
        UmsMember currentMember = umsMemberService.getCurrentMember();
        address.setMemberId(currentMember.getId());
        return addressMapper.insert(address);
    }

    @Override
    public int delete(Long id) {
        UmsMember currentMember = umsMemberService.getCurrentMember();
        return addressMapper.delete(new LambdaQueryWrapper<UmsMemberReceiveAddress>().eq(UmsMemberReceiveAddress::getMemberId, currentMember.getId()).eq(UmsMemberReceiveAddress::getId, id));
    }

    @Override
    public int update(Long id, UmsMemberReceiveAddress address) {
        address.setId(null);
        UmsMember currentMember = umsMemberService.getCurrentMember();
        if(address.getDefaultStatus() == 1){
            // 先将原来的默认地址去除
            UmsMemberReceiveAddress record = new UmsMemberReceiveAddress();
            record.setDefaultStatus(0);
            addressMapper.update(record, new LambdaQueryWrapper<UmsMemberReceiveAddress>().eq(UmsMemberReceiveAddress::getMemberId, currentMember.getId()).eq(UmsMemberReceiveAddress::getDefaultStatus, 1));
        }
        return addressMapper.update(address, new LambdaQueryWrapper<UmsMemberReceiveAddress>().eq(UmsMemberReceiveAddress::getMemberId, currentMember.getId()).eq(UmsMemberReceiveAddress::getId, id));
    }
}
