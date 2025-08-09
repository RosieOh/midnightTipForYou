package com.midnighttipforyou.domain.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class KakaoMessageService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${kakao.access-token}")
    private String kakaoAccessToken;

    public void sendDailyTip() {
        try {
            // tips 안에 파일 읽기
            String tipContent = Files.readString(Path.of("src/main/resources/tips/vuetify_tip.txt"));

            String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

            var templateObj = new LinkedMultiValueMap<String, String>();
            templateObj.add("template_object", new Gson().toJson(
                    new KakaoTemplate("text", tipContent, new Link("https://your-service.com/tip"))
            ));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setBearerAuth(kakaoAccessToken);
            headers.setAcceptCharset(java.util.List.of(StandardCharsets.UTF_8));

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(templateObj, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            System.out.println("카카오톡 발송 결과: " + response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    record KakaoTemplate(String object_type, String text, Link link) {}
    record Link(String web_url) {}
}