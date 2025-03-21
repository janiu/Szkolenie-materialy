package com.org.rjankowski.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TrnVerificatorService {

    private final KafkaTemplate<String, SimpleTrn> kafkaTemplate;

    @Value("${kafka.trn-to-verification-topic}")
    private String trnTOVerificationTopic;

    @KafkaListener(topics = {"credit-card-trn", "online-payment"}, groupId = "transaction-verificator")
    public void listen(SimpleTrn trn) {
        System.out.println("Trn for clientId: " + trn.clientId + " and value: " + trn.value);
        kafkaTemplate.send(trnTOVerificationTopic, trn);
    }

}
