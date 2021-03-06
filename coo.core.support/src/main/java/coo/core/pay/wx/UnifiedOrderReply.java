package coo.core.pay.wx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import coo.base.util.CryptoUtils;

/**
 * 统一下单响应。
 */
public class UnifiedOrderReply extends WxPayReply {
  @JacksonXmlProperty(localName = "device_info")
  private String deviceInfo;
  @JacksonXmlProperty(localName = "trade_type")
  private String tradeType;
  @JacksonXmlProperty(localName = "prepay_id")
  private String prepayId;

  /**
   * 生成支付请求。
   * 
   * @param key 密钥
   * @return 返回支付请求。
   */
  public PayQuery genPayQuery(String key) {
    PayQuery payQuery = new PayQuery();
    payQuery.setAppId(appId);
    payQuery.setPartnerId(mchId);
    payQuery.setPrepayId(prepayId);
    payQuery.setExtendField("Sign=WXPay");
    payQuery.setTimestamp(Long.toString(System.currentTimeMillis() / 1000));
    payQuery.setNonceStr(CryptoUtils.genRandomCode(CryptoUtils.ALL, 16));
    payQuery.setSign(WxPayUtils.sign(payQuery, key));
    return payQuery;
  }

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getTradeType() {
    return tradeType;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  public String getPrepayId() {
    return prepayId;
  }

  public void setPrepayId(String prepayId) {
    this.prepayId = prepayId;
  }
}
