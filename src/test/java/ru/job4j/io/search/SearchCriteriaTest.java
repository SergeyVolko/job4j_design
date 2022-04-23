package ru.job4j.io.search;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class SearchCriteriaTest  {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    public File subFolder;
    public File file1;
    public File file2;
    public File file3;
    public File file4;
    public File file5;
    public File target;

    @Before
    public void init() throws IOException {
        subFolder = folder.newFolder("subfolder");
        file1 = folder.newFile("SomeClass1.java");
        file2 = folder.newFile("SomeClass2.java");
        file3 = folder.newFile("Text1.txt");
        file4 = folder.newFile("Text2.txt");
        file5 = folder.newFile(subFolder.getName() + "\\Text1.txt");
        target = folder.newFile("target.txt");
    }

    @Test
    public void whenWriteFileThenMaskResult() throws IOException {
        String[] args = new String[4];
        args[0] = "-d=" + folder.getRoot().getAbsolutePath();
        args[1] = "-n=SomeClass*.java";
        args[2] = "-t=mask";
        args[3] = "-o=" + target.getAbsolutePath();
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.writeFile(args);
        StringBuilder expected = new StringBuilder();
        expected.append(file1.getAbsoluteFile())
                .append(System.lineSeparator())
                .append(file2.getAbsoluteFile())
                .append(System.lineSeparator());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            in.lines().forEach(l -> rsl.append(l).append(System.lineSeparator()));
        }
       assertThat(rsl.toString(), is(expected.toString()));
    }

    @Test
    public void whenWriteFileThenNameResult() throws IOException {
        String[] args = new String[4];
        args[0] = "-d=" + folder.getRoot().getAbsolutePath();
        args[1] = "-n=Text1.txt";
        args[2] = "-t=name";
        args[3] = "-o=" + target.getAbsolutePath();
        StringBuilder expected = new StringBuilder();
        expected.append(file5.getAbsoluteFile())
                .append(System.lineSeparator())
                .append(file3.getAbsoluteFile())
                .append(System.lineSeparator());
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.writeFile(args);
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            in.lines().forEach(l -> rsl.append(l).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is(expected.toString()));
    }

    @Test
    public void whenWriteFileThenRegexResult() throws IOException {
        String[] args = new String[4];
        args[0] = "-d=" + folder.getRoot().getAbsolutePath();
        args[1] = "-n=\\S+\\.java";
        args[2] = "-t=regex";
        args[3] = "-o=" + target.getAbsolutePath();
        StringBuilder expected = new StringBuilder();
        expected.append(file1.getAbsoluteFile())
                .append(System.lineSeparator())
                .append(file2.getAbsoluteFile())
                .append(System.lineSeparator());
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.writeFile(args);
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            in.lines().forEach(l -> rsl.append(l).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is(expected.toString()));
    }
}