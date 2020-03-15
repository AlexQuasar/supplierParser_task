package com.alexquasar.supplierParser.web.input;

import com.alexquasar.supplierParser.service.ParserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/supplierParser")
public class ParserController {

    private ParserService parserService;

    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

    @PostMapping("/parse")
    public String parse(@RequestParam List<MultipartFile> files) {
        return parserService.parse(files);
    }
}
