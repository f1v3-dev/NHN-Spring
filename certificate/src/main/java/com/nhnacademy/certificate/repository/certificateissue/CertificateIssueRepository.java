package com.nhnacademy.certificate.repository.certificateissue;

import com.nhnacademy.certificate.domain.IssueDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long>,
        CertificateIssueRepositoryCustom {

    IssueDto findByCertificateConfirmationNumber(Long certificateConfirmationNumber);

}
