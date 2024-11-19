package com.agrestina.mail;

import com.agrestina.dto.statistics.BillingReport;
import com.agrestina.dto.statistics.InventoryReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportEmail {

    @Autowired
    private EmailSender sender;

    public void send(InventoryReport inventory, BillingReport billing){
        sender.sendEmail(
                "Relatórios gerados",
                "admin@email.com",
                """
                        Olá!
                        
                        Seus relatórios foram gerados e seguem abaixo!
                        
                        %s
                        
                        %s
                        """.formatted(inventory, billing)
        );

    }
}
