package com.thiagoasd.ecommercebackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thiagoasd.ecommercebackend.Domain.*;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}