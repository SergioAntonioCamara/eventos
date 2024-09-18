/*package com.mindhub.todolist.service;

import com.mindhub.todolist.dto.CustomerDTO;

import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private Map<String, CustomerDTO> customersDatabase = new HashMap<>();
    //Se utiliza un Map para simular una base de datos de clientes,
    // donde la clave es el email del cliente y el valor es el objeto CustomerDTO.

    public void register(CustomerDTO customerDTO) {
        if (!customersDatabase.containsKey(customerDTO.getEmail())) {
            customersDatabase.put(customerDTO.getEmail(), customerDTO);
            System.out.println("Nuevo cliente registrado: " + customerDTO.getName());
        } else {
            System.out.println("Error: El cliente ya está registrado.");
        }
}
*/
