package com.homework.musinsa.link.infra;

import com.homework.musinsa.link.domain.Link;
import org.springframework.data.repository.CrudRepository;

public interface LinkRepository extends CrudRepository<Link, String> {
}
