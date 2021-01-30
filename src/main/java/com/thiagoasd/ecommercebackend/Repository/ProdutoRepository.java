package com.thiagoasd.ecommercebackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thiagoasd.ecommercebackend.Domain.*;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}