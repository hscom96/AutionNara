package com.neoga.communication.chat.client;

import com.neoga.communication.chat.dto.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class MemberClient {
    private final RestTemplate restTemplate;
    @Value("${platformHost}")
    private String platformHost;

    public Members retrieveMemberById(final Long memberId){
        return restTemplate.getForObject(
                platformHost + "/api/member/" + memberId,
                Members.class);
    }
}
