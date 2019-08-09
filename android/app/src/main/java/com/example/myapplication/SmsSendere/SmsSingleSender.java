package com.example.myapplication.SmsSendere;

import com.github.qcloudsms.SmsBase;
import com.github.qcloudsms.SmsSenderUtil;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPClient;
import com.github.qcloudsms.httpclient.HTTPException;
import com.github.qcloudsms.httpclient.HTTPMethod;
import com.github.qcloudsms.httpclient.HTTPRequest;
import com.github.qcloudsms.httpclient.HTTPResponse;
import com.github.qcloudsms.httpclient.DefaultHTTPClient;

import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;


public class SmsSingleSender extends SmsBase {

    private String url = "https://yun.tim.qq.com/v5/tlssmssvr/sendsms";

    public SmsSingleSender(int appid, String appkey) {
        super(appid, appkey, new DefaultHTTPClient());
    }

    public SmsSingleSender(int appid, String appkey, HTTPClient httpclient) {
        super(appid, appkey, httpclient);
    }

    /**
     * 普通单发
     *
     * 普通单发短信接口，明确指定内容，如果有多个签名，请在内容中以【】的方式添加到信息内容中，否则系统将使用默认签名
     *
     * @param type 短信类型，0 为普通短信，1 营销短信
     * @param nationCode 国家码，如 86 为中国
     * @param phoneNumber 不带国家码的手机号
     * @param msg 信息内容，必须与申请的模板格式一致，否则将返回错误
     * @param extend 扩展码，可填空
     * @param ext 服务端原样返回的参数，可填空
     * @return {@link}SmsSingleSenderResult
     * @throws HTTPException  http status exception
     * @throws JSONException  json parse exception
     * @throws IOException    network problem
     */
    public SmsSingleSenderResult send(int type, String nationCode, String phoneNumber,
                                      String msg, String extend, String ext)
            throws HTTPException, JSONException, IOException {

        long random = SmsSenderUtil.getRandom();
        long now = SmsSenderUtil.getCurrentTime();

        JSONObject body = new JSONObject()
            .put("tel", (new JSONObject()).put("nationcode", nationCode).put("mobile", phoneNumber))
            .put("type", type)
            .put("msg", msg)
            .put("sig", Encrypt(this.appkey+"&"+random+"&"+now+"&"+phoneNumber,""))
            .put("time", now)
            .put("extend", SmsSenderUtil.isNotEmpty(extend) ? extend : "")
            .put("ext", SmsSenderUtil.isNotEmpty(ext) ? ext : "");

        HTTPRequest req = new HTTPRequest(HTTPMethod.POST, this.url)
            .addHeader("Conetent-Type", "application/json")
            .addQueryParameter("sdkappid", this.appid)
            .addQueryParameter("random", random)
            .setConnectionTimeout(60 * 1000)
            .setRequestTimeout(60 * 1000)
            .setBody(body.toString());

        // TODO Handle timeout exception
        try {
            // May throw IOException and URISyntaxexception
            HTTPResponse res = httpclient.fetch(req);

            // May throw HTTPException
            handleError(res);

            // May throw JSONException
            return (new SmsSingleSenderResult()).parseFromHTTPResponse(res);
        } catch(URISyntaxException e) {
            throw new RuntimeException("API url has been modified, current url: " + url);
        }
    }

    /**
     * 指定模板单发
     *
     * @param nationCode 国家码，如 86 为中国
     * @param phoneNumber 不带国家码的手机号
     * @param templateId 信息内容
     * @param params 模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
     * @param sign 签名，如果填空，系统会使用默认签名
     * @param extend 扩展码，可填空
     * @param ext 服务端原样返回的参数，可填空
     * @return {@link}SmsSingleSenderResult
     * @throws HTTPException  http status exception
     * @throws JSONException  json parse exception
     * @throws IOException    network problem
     */
    public SmsSingleSenderResult sendWithParam(String nationCode, String phoneNumber, int templateId,
        ArrayList<String> params, String sign, String extend, String ext)
            throws HTTPException, JSONException, IOException {

        long random = SmsSenderUtil.getRandom();
        long now = SmsSenderUtil.getCurrentTime();

        JSONObject body = new JSONObject()
            .put("tel", (new JSONObject()).put("nationcode", nationCode).put("mobile", phoneNumber))
            .put("sig", SmsSenderUtil.calculateSignature(appkey, random, now, phoneNumber))
            .put("tpl_id", templateId)
            .put("params", params)
            .put("sign", sign)
            .put("time", now)
            .put("extend", SmsSenderUtil.isNotEmpty(extend) ? extend : "")
            .put("ext", SmsSenderUtil.isNotEmpty(ext) ? ext : "");

        HTTPRequest req = new HTTPRequest(HTTPMethod.POST, this.url)
            .addHeader("Conetent-Type", "application/json")
            .addQueryParameter("sdkappid", this.appid)
            .addQueryParameter("random", random)
            .setConnectionTimeout(60 * 1000)
            .setRequestTimeout(60 * 1000)
            .setBody(body.toString());

        try {
            // May throw IOException and URISyntaxexception
            HTTPResponse res = httpclient.fetch(req);

            // May throw HTTPException
            handleError(res);

            // May throw JSONException
            return (new SmsSingleSenderResult()).parseFromHTTPResponse(res);
        } catch(URISyntaxException e) {
            throw new RuntimeException("API url has been modified, current url: " + url);
        }
    }

    public SmsSingleSenderResult sendWithParam(String nationCode, String phoneNumber, int templateId,
        String[] params, String sign, String extend, String ext)
            throws HTTPException, JSONException, IOException {

        return sendWithParam(nationCode, phoneNumber, templateId,
            new ArrayList<String>(Arrays.asList(params)), sign, extend, ext);
    }

    private static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }
    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}
