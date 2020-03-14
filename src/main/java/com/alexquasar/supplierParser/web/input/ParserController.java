package com.alexquasar.supplierParser.web.input;

import com.alexquasar.supplierParser.service.ParserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplierParser")
public class ParserController {

    private ParserService parserService;

    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

    @PostMapping("/parse")
    public String parse(@RequestBody byte[] supplierBytes, @RequestBody byte[] receiverBytes) {
        return parserService.parse(supplierBytes, receiverBytes);
    }
}
