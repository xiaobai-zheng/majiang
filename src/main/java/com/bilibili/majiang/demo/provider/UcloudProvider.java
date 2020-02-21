package com.bilibili.majiang.demo.provider;


import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.OnProgressListener;
import com.bilibili.majiang.demo.exception.CustomException;
import com.bilibili.majiang.demo.exception.CustomExceptionCodeImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class UcloudProvider {
    @Value("${ucloud.ufile.public}")
    private String publicKey;
    @Value("${ucloud.ufile.private}")
    private String privateKey;
    @Value("${ucloud.ufile.region}")
    private String region;
    @Value("${ucloud.ufile.proxySuffix}")
    private String proxySuffix;
    @Value("${ucloud.ufile.expires}")
    private  int expiresDuration;
    @Value("${ucloud.ufile.bucketName}")
    private String bucketName;
    public String ucloud(InputStream fileStream,String mimeType,String fileName){
        String[] filePaths = fileName.split("\\.");
        String generatedFilePath;
        if (filePaths.length >1){
            generatedFilePath = UUID.randomUUID().toString()+"."+filePaths[filePaths.length -1];
        }else {
            throw new CustomException(CustomExceptionCodeImpl.UCLOUD_FAIL);
        }
        try {
            ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(publicKey,privateKey);
            ObjectConfig config = new ObjectConfig(region, proxySuffix);
            String bucketName = "zgqmajiang";
            PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
                    .putObject(fileStream, mimeType)
                    .nameAs(generatedFilePath)
                    .toBucket(bucketName)
                    /**
                     * 是否上传校验MD5, Default = true
                     */
                    //  .withVerifyMd5(false)
                    /**
                     * 指定progress callback的间隔, Default = 每秒回调
                     */
                    //  .withProgressConfig(ProgressConfig.callbackWithPercent(10))
                    /**
                     * 配置进度监听
                     */
                    .setOnProgressListener(new OnProgressListener() {
                        @Override
                        public void onProgress(long bytesWritten, long contentLength) {

                        }
                    })
                    .execute();
                    if (response != null && response.getRetCode() == 0){

                        String url = UfileClient.object(objectAuthorization, config)
                                .getDownloadUrlFromPrivateBucket(generatedFilePath, bucketName, expiresDuration)
                                /**
                                 * 使用Content-Disposition: attachment，并且默认文件名为KeyName
                                 */
//                    .withAttachment()
                                /**
                                 * 使用Content-Disposition: attachment，并且配置文件名
                                 */
//                    .withAttachment("filename")
                                .createUrl();
                                return url;
                    }else {
                        throw new CustomException(CustomExceptionCodeImpl.UCLOUD_FAIL);
                    }
        } catch (UfileClientException e) {
            e.printStackTrace();
            throw new CustomException(CustomExceptionCodeImpl.UCLOUD_FAIL);
        } catch (UfileServerException e) {
            e.printStackTrace();
            throw new CustomException(CustomExceptionCodeImpl.UCLOUD_FAIL);
        }
    }
}
