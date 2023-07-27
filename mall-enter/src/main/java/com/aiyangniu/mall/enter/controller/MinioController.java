package com.aiyangniu.mall.enter.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.aiyangniu.mall.common.api.CommonResult;
import com.aiyangniu.mall.enter.model.bo.BucketPolicyConfig;
import com.aiyangniu.mall.enter.model.bo.MinioUpload;
import io.minio.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MinIO对象存储管理
 *
 * @author lzq
 * @date 2023/05/30
 */
@Slf4j
@Api(tags = "MinioController")
@Tag(name = "MinioController", description = "MinIO对象存储管理")
@RestController
@RequestMapping("/minio")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MinioController {

    @Value("${minio.endpoint}")
    private String endPoint;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @ApiOperation("文件上传")
    @PostMapping(value = "/upload")
    public CommonResult upload(@RequestPart("file") MultipartFile file) {
        try {
            // 创建一个MinIO的Java客户端
            MinioClient minioClient =MinioClient.builder()
                    .endpoint(endPoint)
                    .credentials(accessKey, secretKey)
                    .build();
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (isExist) {
                log.info("存储桶已经存在！");
            } else {
                // 创建存储桶并设置只读权限
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                BucketPolicyConfig dto = createBucketPolicyConfig(bucketName);
                SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder()
                        .bucket(bucketName)
                        .config(JSONUtil.toJsonStr(dto))
                        .build();
                minioClient.setBucketPolicy(setBucketPolicyArgs);
            }
            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置存储对象名称
            String objectName = sdf.format(new Date()) + "/" + filename;
            // 使用putObject上传一个文件到存储桶中
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE).build();
            minioClient.putObject(putObjectArgs);
            log.info("文件上传成功!");
            MinioUpload dto = new MinioUpload();
            dto.setName(filename);
            dto.setUrl(endPoint + "/" + bucketName + "/" + objectName);
            return CommonResult.success(dto);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("上传发生错误: {}！", e.getMessage());
        }
        return CommonResult.failed();
    }

    /**
     * 创建存储桶的访问策略，设置为只读权限
     */
    private BucketPolicyConfig createBucketPolicyConfig(String bucketName) {
        BucketPolicyConfig.Statement statement = BucketPolicyConfig.Statement.builder()
                .effect("Allow")
                .principal("*")
                .action("s3:GetObject")
                .resource("arn:aws:s3:::"+bucketName+"/*.**").build();
        return BucketPolicyConfig.builder()
                .version("2012-10-17")
                .statement(CollUtil.toList(statement))
                .build();
    }

    @ApiOperation("文件删除")
    @PostMapping(value = "/delete")
    public CommonResult delete(@RequestParam("objectName") String objectName) {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endPoint)
                    .credentials(accessKey, secretKey)
                    .build();
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
            return CommonResult.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }
}
