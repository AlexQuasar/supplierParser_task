package com.alexquasar.supplierParser.service.interfaces;

import java.io.File;

public interface Parser {

    String parse(File supplierFile, File receiverFile);
}
