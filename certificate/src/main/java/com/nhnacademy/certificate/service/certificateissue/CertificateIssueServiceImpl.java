package com.nhnacademy.certificate.service.certificateissue;

import com.nhnacademy.certificate.domain.IssueDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.repository.certificateissue.CertificateIssueRepository;
import com.nhnacademy.certificate.repository.resident.ResidentRepository;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CertificateIssueServiceImpl implements CertificateIssueService {

    private final CertificateIssueRepository certificateIssueRepository;

    private final ResidentRepository residentRepository;

    public CertificateIssueServiceImpl(CertificateIssueRepository certificateIssueRepository,
                                       ResidentRepository residentRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
        this.residentRepository = residentRepository;
    }

    @Transactional
    @Override
    public CertificateIssue register(Integer residentSerialNumber, String certificateTypeCode) {

        Optional<Resident> optionalResident = residentRepository.findById(residentSerialNumber);

        if (optionalResident.isPresent()) {
            CertificateIssue certificateIssue = new CertificateIssue();

            // 증명서 확인 번호 = 98765432 + random 8자리 숫자
            String sixteenDigits = "98765432" + new Random().nextInt(99999999);

            certificateIssue.setCertificateConfirmationNumber(Long.parseLong(sixteenDigits));
            certificateIssue.setCertificateTypeCode(certificateTypeCode);
            certificateIssue.setCertificateIssueDate(LocalDate.now());
            certificateIssue.setResident(optionalResident.get());

            return certificateIssueRepository.save(certificateIssue);
        }

        throw new ResidentNotFoundException();
    }

    @Override
    public IssueDto findById(Long certificateConfirmationNumber) {
        return certificateIssueRepository.findByCertificateConfirmationNumber(certificateConfirmationNumber);
    }
}
