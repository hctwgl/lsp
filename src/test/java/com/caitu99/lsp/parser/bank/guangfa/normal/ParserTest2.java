package com.caitu99.lsp.parser.bank.guangfa.normal;

import com.caitu99.lsp.model.parser.ParserContext;
import com.caitu99.lsp.model.spider.MailSrc;
import com.caitu99.lsp.parser.ParserReactor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lion on 2015/12/22 0022.
 * 15年12月账单测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class ParserTest2 {
    private static final Logger logger = LoggerFactory.getLogger(ParserTest2.class);

    @Test
    public void testReactor() throws Exception {
        String html = readFile("F:\\工作数据\\邮件解析\\guangfa.html");

        MailSrc mailSrc = new MailSrc();
        mailSrc.setTitle("广发卡12月账单");
        mailSrc.setBody(html);
        mailSrc.setDate(new Date());
        mailSrc.setBank("xxoo");

        List<MailSrc> mailSrcList = new ArrayList<>();
        mailSrcList.add(mailSrc);

        ParserContext context = new ParserContext();
        context.setUserId(1);
        context.setAccount("bobo@qq.com");
        context.setMailSrcs(mailSrcList);

        ParserReactor.getInstance().processDebug(context);
        logger.info("");
    }

    private String readFile( String file ) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }

        return stringBuilder.toString();
    }
}
