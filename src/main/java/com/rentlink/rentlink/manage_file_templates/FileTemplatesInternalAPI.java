package com.rentlink.rentlink.manage_file_templates;

import java.util.Map;

public interface FileTemplatesInternalAPI {

    Object generateFromTemplate(Map<String, String> dataContext, DocTemplate template);
}
