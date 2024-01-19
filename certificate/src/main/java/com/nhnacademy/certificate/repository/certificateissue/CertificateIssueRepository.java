package com.nhnacademy.certificate.repository.certificateissue;

import com.nhnacademy.certificate.domain.IssueDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {

    IssueDto findByCertificateConfirmationNumber(Long certificateConfirmationNumber);
}
