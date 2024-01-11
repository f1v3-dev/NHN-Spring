package com.nhnacademy.springmvc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

class StudentRestControllerTest {

    private MockMvc mockMvc;

    private StudentRepository studentRepository;

    Student student;

    String xmlStudent;

    String jsonStudent;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        studentRepository = mock(StudentRepository.class);
        StudentRestController controller = new StudentRestController(studentRepository);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        student = Student.create("테스터", "test@nhnacademy.com", 75, "테스터");
        student.setId(1L);


        xmlStudent = "<Student>" + "<id>1</id>" + "<name>테스터</name>" + "<email>test@nhnacademy.com</email>" +
                "<score>75</score>" + "<comment>테스터</comment>" + "</Student>";

        jsonStudent = new ObjectMapper().writeValueAsString(student);
    }

    @Test
    @DisplayName("학생 정보 조회 : 성공 (JSON)")
    void viewStudent_Success_Json() throws Exception {

        when(studentRepository.getStudent(anyLong())).thenReturn(student);


        mockMvc.perform(get("/students/{studentId}", 1).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(content().json(jsonStudent))
                .andDo(print());

        Mockito.verify(studentRepository, times(1)).getStudent(anyLong());
    }

    @Test
    @DisplayName("학생 정보 조회 : 성공 (XML)")
    void viewStudent_Success_Xml() throws Exception {

        when(studentRepository.getStudent(anyLong())).thenReturn(student);

        mockMvc.perform(get("/students/{studentId}", 1)
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML + ";charset=UTF-8"))
                .andExpect(content().xml(xmlStudent)).andDo(print());

        Mockito.verify(studentRepository, times(1)).getStudent(anyLong());
    }

    @Test
    @DisplayName("학생 정보 조회 : 실패 (존재하지 않는 학생일 경우)")
    void viewStudent_Fail() {

        when(studentRepository.getStudent(anyLong())).thenReturn(null);

        Throwable throwable = catchThrowable(() ->
                mockMvc.perform(get("/students/{studentId}", 1))
                        .andDo(print()));

        assertThat(throwable).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(StudentNotFoundException.class);

        Mockito.verify(studentRepository, times(1)).getStudent(anyLong());

    }

    @Test
    @DisplayName("학생 정보 등록 : 성공 (JSON)")
    void registerStudent_Success_Json() throws Exception {

        Student newStudent = Student.create("테스터", "test@nhnacademy.com", 75, "테스터");
        when(studentRepository.register(anyString(), anyString(), anyInt(), anyString())).thenReturn(newStudent);

        String expectResult = new ObjectMapper().writeValueAsString(newStudent);

        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(expectResult))
                .andExpect(status().isCreated());

        Mockito.verify(studentRepository, times(1)).register(anyString(), anyString(), anyInt(), anyString());
    }

    @Test
    @DisplayName("학생 정보 등록 : 성공 (XML)")
    void registerStudent_Success_Xml() throws Exception {


        Student newStudent = Student.create("테스터", "test@nhnacademy.com", 75, "테스터");
        when(studentRepository.register(anyString(), anyString(), anyInt(), anyString())).thenReturn(newStudent);

        String expectResult = "<Student>\n" + "    <name>테스터</name>\n" + "    <email>test@nhnacademy.com</email>\n" +
                "    <score>75</score>\n" + "    <comment>테스터</comment>\n" + "</Student>\n";

        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_XML).content(expectResult))
                .andExpect(status().isCreated());

        Mockito.verify(studentRepository, times(1)).register(anyString(), anyString(), anyInt(), anyString());
    }


    @Test
    @DisplayName("학생 정보 등록 : 실패 (@Valid)")
    void registerStudent_Fail() {

        String invalidRegister =
                "<Student>\n" + "    <name></name>\n" + "    <email>INVALID</email>\n" + "    <score>9999</score>\n" +
                        "    <comment></comment>\n" + "</Student>\n";

        Throwable throwable = catchThrowable(
                () -> mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_XML).content(invalidRegister))
                        .andDo(print()));

        assertThat(throwable).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);

        Mockito.verify(studentRepository, times(0)).register(anyString(), anyString(), anyInt(), anyString());
    }

    @Test
    @DisplayName("학생 정보 수정 : 성공")
    void modifyStudent_Success() throws Exception {


        when(studentRepository.exists(anyLong())).thenReturn(true);
        doNothing().when(studentRepository).modify(anyLong(), any());


        mockMvc.perform(put("/students/{studentId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStudent))
                .andExpect(status().isOk()).andDo(print());

        Mockito.verify(studentRepository, times(1)).modify(anyLong(), any());
    }

    @Test
    @DisplayName("학생 정보 수정 : 실패 (존재하지 않는 학생)")
    void modifyStudent_Fail_Not_Found() {

        when(studentRepository.exists(anyLong())).thenReturn(false);

        Throwable throwable = catchThrowable(() -> mockMvc.perform(
                        put("/students/{studentId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonStudent))
                .andDo(print()));

        assertThat(throwable).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(StudentNotFoundException.class);

        Mockito.verify(studentRepository, times(0)).modify(anyLong(), any());
    }

    @Test
    @DisplayName("학생 정보 수정 : 실패 (@Valid)")
    void modifyStudent_Fail_Invalid() throws JsonProcessingException {


        Student invalidStudent = Student.create("", "INVALID", 9999, "");
        String invalid = new ObjectMapper().writeValueAsString(invalidStudent);

        Throwable throwable = catchThrowable(() -> mockMvc.perform(
                        put("/students/{studentId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invalid))
                .andDo(print()));

        assertThat(throwable).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);

        Mockito.verify(studentRepository, times(0)).modify(anyLong(), any());
    }
}