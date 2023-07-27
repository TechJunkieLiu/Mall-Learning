package com.aiyangniu.mall.enter.service;

import com.aiyangniu.mall.enter.model.bo.OssCallbackResult;
import com.aiyangniu.mall.enter.model.bo.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss对象存储管理接口
 *
 * @author lzq
 * @date 2023/05/29
 */
public interface OssService {

    /**
     * Oss上传策略生成
     *
     * @return 上传策略
     */
    OssPolicyResult policy();

    /**
     * Oss上传成功回调
     *
     * @param request 上传请求
     * @return 上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
