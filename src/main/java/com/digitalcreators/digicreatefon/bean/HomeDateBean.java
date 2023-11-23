package com.digitalcreators.digicreatefon.bean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.digitalcreators.digicreatefon.dto.SystemDateDto;
import com.digitalcreators.digicreatefon.model.SystemDate;
import com.digitalcreators.digicreatefon.service.SystemDateService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextHolder;

import static com.digitalcreators.digicreatefon.utils.Constants.CASH_TRANSACTION_ARCHIVE_URL;
import static com.digitalcreators.digicreatefon.utils.Constants.FUND_TRANSACTION_ARCHIVE_URL;

@Named(value = "homeDateBean")
@RequestScope
public class HomeDateBean implements Serializable {

    private String nowDate;

    @Inject
    private SystemDateService systemDateService;

    @PostConstruct
    public void init() {
        List<SystemDate> systemDates = getAllSystemDates();
        if (!systemDates.isEmpty()) {
            SystemDate latestSystemDate = getLatestSystemDate(systemDates);
            nowDate = formatDate(latestSystemDate.getSystem_date());
        } else {
            nowDate = "Sistem Tarihi Girilmemiş!";
        }
    }

    private List<SystemDate> getAllSystemDates() {
        return systemDateService.gelAllSystemDates();
    }

    private SystemDate getLatestSystemDate(List<SystemDate> systemDates) {
        return systemDates.get(systemDates.size() - 1);
    }

    private String formatDate(java.sql.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

    public void nextDay() throws ParseException {
        java.sql.Date today = stringToDate(getNowDate());
        SystemDateDto todaySystemDateDto = new SystemDateDto(today);

        String cashTriggerResult = triggerService(todaySystemDateDto, CASH_TRANSACTION_ARCHIVE_URL);
        String fundTriggerResult = triggerService(todaySystemDateDto, FUND_TRANSACTION_ARCHIVE_URL);

        today = SystemDateDto.getNextDay(today);
        systemDateService.updateLatestSystemDate(new SystemDateDto(today));
        refreshPage();

    }

    private void refreshPage() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            fc.getExternalContext().redirect("home.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String triggerService(SystemDateDto systemDateDto, String triggerUrl) {
        try {
            Map<String, Object> requestBody = constructRequestBodyForNextDay(systemDateDto);
            HttpEntity<Map<String, Object>> entity = prepareHttpEntity(requestBody);

            Boolean triggerResult = callTriggerService(entity, triggerUrl);

            if (triggerResult) {
                return handleSuccessfulTrigger();
            } else {
                return handleFailedTrigger();
            }
        } catch (Exception e) {
            return handleUnexpectedError(e);
        }
    }

    private HttpEntity<Map<String, Object>> prepareHttpEntity(Map<String, Object> requestBody) {
        HttpHeaders headers = createHttpHeaders();
        return new HttpEntity<>(requestBody, headers);
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        headers.add("Cookie", "JSESSIONID=" + sessionId);
        return headers;
    }

    private Map<String, Object> constructRequestBodyForNextDay(SystemDateDto systemDateDto) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("system_date", systemDateDto.getSystem_date());
        requestBody.put("system_date_time", systemDateDto.getSystem_date_time());
        return requestBody;
    }

    private Boolean callTriggerService(HttpEntity<Map<String, Object>> entity, String triggerUrl) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.postForEntity(triggerUrl, entity, Boolean.class);
        return response.getBody();
    }

    private String handleSuccessfulTrigger() {
        return "Trigger successful!";
    }

    private String handleFailedTrigger() {
        return "Trigger failed!";
    }

    private String handleUnexpectedError(Exception e) {
        return "Unexpected error: " + e.getMessage();
    }

    private java.sql.Date stringToDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date parsedDate = sdf.parse(dateStr);
        return new java.sql.Date(parsedDate.getTime());
    }

    public String getNowDate() {
        return nowDate;
    }
}
