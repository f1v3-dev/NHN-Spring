package com.nhnacademy.certificate.service.certificateissue;

import com.nhnacademy.certificate.domain.IssueDto;
import com.nhnacademy.certificate.domain.IssueListDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertificateIssueService {

    CertificateIssue register(Integer residentSerialNumber, String certificateTypeCode);

    IssueDto findById(Long certificateConfirmationNumber);

    Page<IssueListDto> findBySerialNumber(Integer serialNumber, Pageable pageable);
}
