package com.nhnacademy.certificate.service.certificateissue;

import com.nhnacademy.certificate.domain.IssueDto;
import com.nhnacademy.certificate.entity.CertificateIssue;

public interface CertificateIssueService {

    CertificateIssue register(Integer residentSerialNumber, String certificateTypeCode);

    IssueDto findById(Long certificateConfirmationNumber);
}
