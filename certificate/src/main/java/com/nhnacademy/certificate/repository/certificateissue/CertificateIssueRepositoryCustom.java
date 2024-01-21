package com.nhnacademy.certificate.repository.certificateissue;

import com.nhnacademy.certificate.domain.IssueListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertificateIssueRepositoryCustom {

    Page<IssueListDto> findPagesByResident(Integer serialNumber, Pageable pageable);

}
