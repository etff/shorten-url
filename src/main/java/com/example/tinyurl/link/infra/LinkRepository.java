package com.example.tinyurl.link.infra;

import com.example.tinyurl.link.domain.Link;
import org.springframework.data.repository.CrudRepository;

public interface LinkRepository extends CrudRepository<Link, String> {
}
