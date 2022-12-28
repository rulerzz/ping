package com.ping.services.onboardingservice.service;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.ping.services.onboardingservice.dto.OnboardingResponseDTO;
import com.ping.services.onboardingservice.dto.OnboardingStartDTO;
import com.ping.services.onboardingservice.dto.UpdateUserNameDTO;
import com.ping.services.onboardingservice.exceptions.OnboardingException;
import com.ping.services.onboardingservice.model.Account;
import com.ping.services.onboardingservice.model.Contact;
import com.ping.services.onboardingservice.model.PersonalInfo;
import com.ping.services.onboardingservice.repository.AccountRepository;
import com.ping.services.onboardingservice.repository.ContactRepository;
import com.ping.services.onboardingservice.repository.PersonalInfoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OnboardingService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PersonalInfoRepository personalInfoRepository;
    @Autowired
    private ContactRepository contactRepository;
    private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    @Transactional
    public OnboardingResponseDTO startOnboarding(OnboardingStartDTO onboardingRequest){
        OnboardingResponseDTO response = new OnboardingResponseDTO();
        Account account = new Account();
        Optional<Account> checkPhone;
        Optional<Account> checkEmail;
        String phoneToBeChecked;
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
        try{
            if(onboardingRequest.getPhoneNumber() == null && onboardingRequest.getEmail() == null)
                throw new OnboardingException("Phone number or email must be provided to onboard");

            if(onboardingRequest.getPhoneNumber() != null) {
                phoneNumber = phoneUtil.parseAndKeepRawInput(onboardingRequest.getPhoneNumber(), onboardingRequest.getDefaultCountry());
                if(phoneUtil.isValidNumber(phoneNumber)) {
                    phoneToBeChecked = Integer.toString(phoneNumber.getCountryCode()).concat(Long.toString(phoneNumber.getNationalNumber()));
                    account.setPhoneNumber(phoneToBeChecked);
                    checkPhone = accountRepository.findByPhoneNumber(phoneToBeChecked);
                    if(checkPhone.isPresent())
                        throw new OnboardingException("This phone number cannot be used to onboard");
                }
                else
                throw new OnboardingException("This phone number cannot be used to onboard");
            }
            if(onboardingRequest.getEmail() != null) {
                checkEmail = accountRepository.findByEmail(onboardingRequest.getEmail());
                if(checkEmail.isPresent())
                    throw new OnboardingException("This email cannot be used to onboard");
                account.setEmail(onboardingRequest.getEmail());
            }

            accountRepository.save(account);

            PersonalInfo personalInfo = new PersonalInfo(onboardingRequest.getFirstName(), onboardingRequest.getLastName(), onboardingRequest.getMiddleName(), onboardingRequest.getDob(), account);
            Contact contact = new Contact(phoneNumber.getCountryCode(), phoneNumber.getNationalNumber(), onboardingRequest.getEmail(), account);

            account.setContact(contact);
            account.setPersonalInfo(personalInfo);

            personalInfoRepository.save(personalInfo);
            contactRepository.save(contact);
            accountRepository.save(account);

            response.setStatus("success");
            response.setMessage("created account successfully");
            response.setStatusCode(201);
            response.setData(account);
        } catch(Exception ex){
            log.info("Exception encountered with message for input {} with message {}", onboardingRequest.toString() ,ex.getMessage());
            response.setStatus("failed");
            response.setMessage(ex.getMessage());
            response.setStatusCode(400);
        }
        return response;
    }

    public List<Account> listAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public OnboardingResponseDTO updateUserName(UpdateUserNameDTO updateRequest) {
        OnboardingResponseDTO response = new OnboardingResponseDTO();
        Optional<Account> searchByPhone;
        Optional<Account> searchByEmail;
        Account account = new Account();
        try{
            if(updateRequest.getPhoneNumber() == null && updateRequest.getEmail() == null)
                throw new OnboardingException("Phone number or email must be provided to onboard");
            if(updateRequest.getPhoneNumber() != null){
                searchByPhone = this.accountRepository.findByPhoneNumber(updateRequest.getPhoneNumber());
                if(searchByPhone.isEmpty())
                    throw new OnboardingException("The phone number provided does not exist");
                account = searchByPhone.get();
            }
            if(updateRequest.getEmail() != null){
                searchByEmail = this.accountRepository.findByPhoneNumber(updateRequest.getPhoneNumber());
                if(searchByEmail.isEmpty())
                    throw new OnboardingException("The email provided does not exist");
                account = searchByEmail.get();
            }
            account.setUserName(updateRequest.getUserName());
            accountRepository.save(account);
            response.setStatusCode(200);
            response.setStatus("success");
            response.setMessage("Updated username successfully");
            response.setData(account);
        }catch(Exception ex){
            log.info("Exception encountered with message for input {} with message {}", updateRequest.toString() ,ex.getMessage());
            response.setStatusCode(400);
            response.setStatus("error");
            response.setMessage(ex.getMessage());
        }
        return response;
    }
}
