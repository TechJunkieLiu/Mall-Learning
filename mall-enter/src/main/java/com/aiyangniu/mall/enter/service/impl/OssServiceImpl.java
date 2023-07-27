package com.aiyangniu.mall.enter.service.impl;

import cn.hutool.json.JSONUtil;
import com.aiyangniu.mall.enter.model.bo.OssCallbackParam;
import com.aiyangniu.mall.enter.model.bo.OssCallbackResult;
import com.aiyangniu.mall.enter.model.bo.OssPolicyResult;
import com.aliyun.oss.OSSClient;
import com.aiyangniu.mall.enter.service.OssService;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Oss对象存储管理实现类
 *
 * @author lzq
 * @date 2023/05/29
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OssServiceImpl implements OssService {

	@Value("${aliyun.oss.policy.expire}")
	private int aLiYunOssPolicyExpire;

	@Value("${aliyun.oss.maxSize}")
	private int aLiYunOssMaxSize;

	@Value("${aliyun.oss.callback}")
	private String aLiYunOssCallback;

	@Value("${aliyun.oss.bucketName}")
	private String aLiYunOssBucketName;

	@Value("${aliyun.oss.endpoint}")
	private String aLiYunOssEndpoint;

	@Value("${aliyun.oss.dir.prefix}")
	private String aLiYunOssDirPrefix;

	private final OSSClient ossClient;

	/**
	 * 签名生成
	 */
	@Override
	public OssPolicyResult policy() {
		OssPolicyResult result = new OssPolicyResult();
		// 存储目录
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dir = aLiYunOssDirPrefix + sdf.format(new Date());
		// 签名有效期
		long expireEndTime = System.currentTimeMillis() + aLiYunOssPolicyExpire * 1000;
		Date expiration = new Date(expireEndTime);
		// 文件大小
		long maxSize = aLiYunOssMaxSize * 1024 * 1024;
		// 回调
		OssCallbackParam callback = new OssCallbackParam();
		callback.setCallbackUrl(aLiYunOssCallback);
		callback.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
		callback.setCallbackBodyType("application/x-www-form-urlencoded");
		// 提交节点
		String action = "http://" + aLiYunOssBucketName + "." + aLiYunOssEndpoint;
		try {
			PolicyConditions policyConds = new PolicyConditions();
			policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
			policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
			String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
			byte[] binaryData = postPolicy.getBytes("utf-8");
			String policy = BinaryUtil.toBase64String(binaryData);
			String signature = ossClient.calculatePostSignature(postPolicy);
			String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callback).toString().getBytes("utf-8"));
			// 返回结果
			result.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
			result.setPolicy(policy);
			result.setSignature(signature);
			result.setDir(dir);
			result.setCallback(callbackData);
			result.setHost(action);
		} catch (Exception e) {
			log.error("签名生成失败", e);
		}
		return result;
	}

	@Override
	public OssCallbackResult callback(HttpServletRequest request) {
		OssCallbackResult result = new OssCallbackResult();
		String filename = request.getParameter("filename");
		filename = "http://".concat(aLiYunOssBucketName).concat(".").concat(aLiYunOssEndpoint).concat("/").concat(filename);
		result.setFilename(filename);
		result.setSize(request.getParameter("size"));
		result.setMimeType(request.getParameter("mimeType"));
		result.setWidth(request.getParameter("width"));
		result.setHeight(request.getParameter("height"));
		return result;
	}
}
