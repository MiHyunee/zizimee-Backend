package com.zizimee.api.pimanager.request.service;

import com.zizimee.api.pimanager.log.entity.ConsentForm;
import com.zizimee.api.pimanager.log.entity.ConsentFormRepository;
import com.zizimee.api.pimanager.log.entity.ConsentStatus;
import com.zizimee.api.pimanager.log.entity.ConsentStatusRepository;
import com.zizimee.api.pimanager.request.entity.Request;
import com.zizimee.api.pimanager.request.entity.Response;
import com.zizimee.api.pimanager.request.entity.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

@RequiredArgsConstructor
@Service
public class ResponseService {
    private final ResponseRepository responseRepository;
    private final ConsentStatusRepository consentStatusRepository;
    private final ConsentFormRepository consentFormRepository;

    public void save(Request request) {
        String content;

        System.out.println(request.getStartDate());
        ConsentStatus beforeConsentStatus = consentStatusRepository.findByDate(request.getStartDate()).orElseThrow(()->new IllegalArgumentException(("동의 여부 파일이 없습니다")));
        ConsentStatus afterConsentStatus = consentStatusRepository.findByDate(request.getEndDate()).orElseThrow(()->new IllegalArgumentException(("동의 여부 파일이 없습니다")));
        Long consentFormId = beforeConsentStatus.getFormId().getId();
        ConsentForm consentForm = consentFormRepository.findById(consentFormId).orElseThrow(()-> new IllegalArgumentException("form이 없습니다"));
        if (isSameConsentStatus(beforeConsentStatus.getId(), afterConsentStatus.getId())) {
            content = "변동 없음\n";
        } else {
            content = "변동 있음\n";
        }

        Response response = Response.builder()
                .content(content)
                .request(request)
                .build();
        responseRepository.save(response);
    }

    private boolean isSameConsentStatus(Long beforeId, Long afterId) {
        return beforeId.equals(afterId);
    }

    private HashMap<String, Character[]> makeForm(ConsentForm consentForm, ConsentStatus consentStatus) {
        HashMap<String, Character[]> form = new HashMap<>();

        String item = consentForm.getConsentItem();
        String status = consentStatus.getIsConsent();
        List<String> itemList = parseItem(item);
        List<Character> consentList = parseConsent(status);

        for(int i=0; i<itemList.size(); i++) {
            Character[] consentArr = {consentList.get(i)};
            form.put(itemList.get(i), consentArr);
        }
        return form;
    }

    private HashMap<String, Character[]> makeForm(ConsentForm consentForm, ConsentStatus beforeConsentStatus, ConsentStatus afterConsentStatus) {
        HashMap<String, Character[]> form = new HashMap<>();

        String item = consentForm.getConsentItem();
        String beforeStatus = beforeConsentStatus.getIsConsent();
        String afterStatus = afterConsentStatus.getIsConsent();

        List<String> itemList = parseItem(item);
        List<Character> beforeConsentList = parseConsent(beforeStatus);
        List<Character> afterConsentList = parseConsent(afterStatus);

        for(int i=0; i<itemList.size(); i++) {
            Character[] consentArr = {beforeConsentList.get(i), afterConsentList.get(i)};
            form.put(itemList.get(i), consentArr);
        }
        return form;
    }

    private List<String> parseItem(String item) {
        final String delimeter = ";" ;
        List<String> itemList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(item, delimeter);

        while(tokenizer.hasMoreTokens()) {
            itemList.add(tokenizer.nextToken());
        }
        return itemList;
    }

    private List<Character> parseConsent(String status) {
        List<Character> itemList = new ArrayList<>();

        for(int i=0; i<status.length(); i++) {
            itemList.add(status.charAt(i));
        }
        return itemList;
    }

    public HashMap<String, Character[]> getForm(Request request) {
        Response response = responseRepository.findByIdRequest(request.getId())
                .orElseThrow(()-> new IllegalArgumentException("해당 요청에 대한 응답이 없습니다."));
        ConsentStatus beforeConsentStatus = consentStatusRepository.findByDate(request.getStartDate()).orElseThrow(()->new IllegalArgumentException(("동의 여부 파일이 없습니다")));
        ConsentStatus afterConsentStatus = consentStatusRepository.findByDate(request.getEndDate()).orElseThrow(()->new IllegalArgumentException(("동의 여부 파일이 없습니다")));
        Long consentFormId = beforeConsentStatus.getFormId().getId();
        ConsentForm consentForm = consentFormRepository.findById(consentFormId).orElseThrow(()-> new IllegalArgumentException("form이 없습니다"));
        if(response.getContent().equals("변동 없음\n")) {
            return makeForm(consentForm, beforeConsentStatus);
        }
        else {
            return makeForm(consentForm, beforeConsentStatus, afterConsentStatus);
        }
    }
}
