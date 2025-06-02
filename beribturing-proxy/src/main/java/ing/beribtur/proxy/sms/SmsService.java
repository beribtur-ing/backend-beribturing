package ing.beribtur.proxy.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService {
    //
    @Value("${sms.gateway.token}")
    private String smsGatewayToken;
    @Value("${sms.gateway.url}")
    private String smsGatewayUrl;

    @Value("${sms.template.sign-up}")
    private String signUpTemplate;

    @Value("${sms.template.reset-password}")
    private String changePasswordTemplate;

    @Value("${sms.template.test}")
    private String testTemplate;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendSms(String phoneNumber, String smsContent) {
        //
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + smsGatewayToken);
        MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();

        try {
            data.add("mobile_phone", phoneNumber);
            data.add("message", smsContent);

            // POST form data
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(data, headers);
            restTemplate.postForEntity(smsGatewayUrl, requestEntity, String.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("OTP (code) has been sent to " + phoneNumber);
    }

    public void sendSignUpOTP(String phoneNumber, Number otp) {
        //
        String smsContent = String.format("%s %s", signUpTemplate, otp);
        this.sendSms(phoneNumber, smsContent);
    }

    public void sendChangePasswordOTP(String phoneNumber, Number otp) {
        //
        String smsContent = String.format("%s %s", changePasswordTemplate, otp);
        this.sendSms(phoneNumber, smsContent);
    }
}
