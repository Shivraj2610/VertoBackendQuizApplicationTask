package com.verto.quiz.service.impl;

import com.verto.quiz.custom.PageableResponse;
import com.verto.quiz.dto.*;
import com.verto.quiz.entity.Option;
import com.verto.quiz.entity.Question;
import com.verto.quiz.entity.Quiz;
import com.verto.quiz.exception.IncorrectRequestExceptionHandler;
import com.verto.quiz.exception.ResourceNotFoundException;
import com.verto.quiz.helper.Helper;
import com.verto.quiz.repository.QuestionRepository;
import com.verto.quiz.repository.QuizRepository;
import com.verto.quiz.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private QuizRepository quizRepository;


    @Autowired
    private QuestionRepository questionRepository;

    //Create new Quiz
    @Override
    public QuizDto createQuiz(QuizDto quizDto) {

        //Create Random Quiz id
        String quizId = UUID.randomUUID().toString();
        //Create the Quiz Created Date
        LocalDate currentDate = LocalDate.now();

        //Set the quizId and current date
        quizDto.setQuizId(quizId);
        quizDto.setCreatedDate(currentDate);

        //Convert the QuizDto into Quiz entity
        Quiz quiz = mapper.map(quizDto, Quiz.class);

        //Save the Quiz
        quizRepository.save(quiz);

        //Return the QuizDto Object
        return mapper.map(quiz, QuizDto.class);
    }


    //Get Single Quiz by quizId
    @Override
    public QuizDto getSingleQuiz(String quizId) {

        Quiz quiz = quizRepository.findById(quizId).orElseThrow(
                () -> new ResourceNotFoundException("Quiz not found by Id: " + quizId)
        );

        //Return the QuizDto
        return mapper.map(quiz, QuizDto.class);
    }


    //Get All Quiz
    @Override
    public PageableResponse<QuizDto> allQuiz(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("asc"))
                    ?Sort.by(sortBy).ascending()
                    :Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNumber, pageSize, sort);
        Page<Quiz> page = quizRepository.findAll(pageable);

        PageableResponse<QuizDto> pageableResponse = Helper.getPageableResponse(page, QuizDto.class);

        return pageableResponse;
    }


    @Override
    public List<QuestionDto> fetchQuizQuestion(String quizId) {

        //Get the Quiz by quizId
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(
                () -> new ResourceNotFoundException("The Quiz not found by Id: " + quizId)
        );

        //Fetch the Questions from quiz
        List<Question> questions = quiz.getQuestions();
        if(questions.size()<=0){
            throw  new ResourceNotFoundException("Questions not available in this quiz");
        }

        return questions.stream().map(que ->{
            return mapper.map(que, QuestionDto.class);
        }).toList();
    }



    @Override
    public void removeQuizQuestion(String quizId, String questionId) {
        //Get Quiz by quizId
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(
                () -> new ResourceNotFoundException("Quiz not found by Id: " + quizId)
        );

        //Get the Question by questionId
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new ResourceNotFoundException("Question not found by Id: " + questionId)
        );

        //Get the Questions from quiz
        List<Question> questions = quiz.getQuestions();
        if(!questions.contains(question)){
            throw new ResourceNotFoundException("Question not found bu Id: "+questionId);
        }

        //Remove the question from Quiz
        questions.remove(question);

        //Remove the Question from Question
        questionRepository.delete(question);

        //Save the Updated Quiz
        quizRepository.save(quiz);
    }

    @Override
    public void removeQuiz(String quizId) {
        //Get Quiz by quizId
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(
                () -> new ResourceNotFoundException("Quiz not found by Id: " + quizId)
        );

        quizRepository.delete(quiz);
    }


    //Update Quiz
    @Override
    public QuizDto updateQuiz(String quizId,QuizDto quizDto) {

        //Get Quiz by quizId
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(
                () -> new ResourceNotFoundException("Quiz not found by Id: " + quizId)
        );

        if(quizDto.getTitle()!=null){
            quiz.setTitle(quizDto.getTitle());
        }

        //Save the Updated Quiz
        Quiz updatedQuiz = quizRepository.save(quiz);

        return mapper.map(updatedQuiz,QuizDto.class);
    }


    //Update Question in Quiz
    @Override
    public QuestionDto updateQuestion(String quizId, String questionId, QuestionDto questionDto) {

        //Get the Quiz by QuizId
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(
                () -> new ResourceNotFoundException("Quiz not found by Id: " + quizId)
        );

        //Find the Question from Quiz by matching the questionId
        Question currentQuestion = quiz.getQuestions().stream()
                .filter(que -> que.getQuestionId().equals(questionId))
                .findFirst()
                .orElseThrow(
                        () -> new ResourceNotFoundException("Question not found in Quiz")
                );

        //Update the QuestionTest
        if(questionDto.getQuestionText()!=null){
            currentQuestion.setQuestionText(questionDto.getQuestionText());
        }


        //Get the Current Question's Options
        List<Option> options = currentQuestion.getOptions();

        //Now Update the current Options
        List<OptionDto> newOptionDto = questionDto.getOptions();

//        List<Option> currentOption = currentOptionDto.stream().map(option -> mapper.map(option, Option.class)).collect(Collectors.toList());

        int currentOptionsSize=options.size();
        int newOptionsSize=newOptionDto.size();

        int minNumberOptions = Math.min(currentOptionsSize, newOptionsSize);


        //Update the Options Details
        int i=0;
        for(i=0; i<minNumberOptions; i++){
            options.get(i).setOptionText(newOptionDto.get(i).getOptionText());
            options.get(i).setCorrect(newOptionDto.get(i).isCorrect());
        }

        currentQuestion.setOptions(options);

        Question updatedQuestion = questionRepository.save(currentQuestion);

        return mapper.map(updatedQuestion, QuestionDto.class);
    }



    //Add Single Question and it's Options
    @Override
    public QuestionDto addQuestionToQuiz(String quizId, QuestionDto questionDto) {

        //Create the Random questionId for each question
        String questionId = UUID.randomUUID().toString();
        questionDto.setQuestionId(questionId);
        // Get the Quiz by quizId
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(
                () -> new ResourceNotFoundException("Quiz not found by Id: " + quizId)
        );

        //Check the Question is Already Exist if yes then Give message
        boolean questionContain=false;
        for(Question que: quiz.getQuestions()){
            if(que.getQuestionText().equals(questionDto.getQuestionText())){
                questionContain=true;
                break;
            }
        }
        if(questionContain){
            throw new IncorrectRequestExceptionHandler("The Question already exist");
        }

        // Convert the QuestionDto to Question Entity
        Question question = mapper.map(questionDto, Question.class);
        //Set the Quiz into question
        question.setQuiz(quiz);

        // Save the Options and Set Correct Option
        int count=0;
        List<Option> options = new ArrayList<>();
        Option correctOption = null;
        for (OptionDto optionDto : questionDto.getOptions()) {
            Option option = new Option(optionDto.getOptionText(), question, optionDto.isCorrect());
            options.add(option);

            if (optionDto.isCorrect()) {
                count++;
                correctOption = option;
            }
        }

        if(correctOption==null){
            throw new IncorrectRequestExceptionHandler("There is no Correct Option are available");
        }

        if(count>1){
            throw new IncorrectRequestExceptionHandler("There are more than one correct option");
        }

        // Set options and correct option for the question
        question.setOptions(options);
        question.setCorrectOption(correctOption);

        //Save Question in Database
        Question savedQuestion = questionRepository.save(question);

        //Add the new Question and Save Quiz in Database
        quiz.getQuestions().add(savedQuestion);
        quizRepository.save(quiz);

        return mapper.map(savedQuestion, QuestionDto.class);
    }


    //Add Multiple Questions in Quiz
    public List<QuestionDto> addMultipleQuestions(String quizId, List<QuestionDto> multipleQuestionDto){

        //Get the Quiz by quizId
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(
                () -> new ResourceNotFoundException("The Quiz not found by Id: " + quizId)
        );


        List<QuestionDto> questionsDtoForResponse = new ArrayList<>();

        for(QuestionDto questionDto: multipleQuestionDto){

            boolean questionExist=false;
            questionExist= quiz.getQuestions().stream()
                    .anyMatch(que -> que.getQuestionText().equals(questionDto.getQuestionText()));

            if(questionExist){
                questionDto.setQuestionTextExist(true);
                questionsDtoForResponse.add(questionDto);
            }else{



            //Set the Unique questionId
            String questionId = UUID.randomUUID().toString();
            questionDto.setQuestionId(questionId);

            //Convert the QuestionDto into Question Entity
            Question question = mapper.map(questionDto, Question.class);

            //Set the Quiz for the Question
            question.setQuiz(quiz);


            //Save the Options and set CorrectOption
            List<Option> options = new ArrayList<>();
            Option correctOption = null;
            int count=0;

            for(OptionDto optionDto: questionDto.getOptions()){

                Option option = new Option(optionDto.getOptionText(), question, optionDto.isCorrect());

                options.add(option);

                //if the optionDto is true then set the correctOption for the question
                if(optionDto.isCorrect()){
                    count++;
                    correctOption=option;
                }
            }

            if(correctOption==null){
                throw new IncorrectRequestExceptionHandler("There is no Correct Option are available");
            }

            if(count>1){
                throw new IncorrectRequestExceptionHandler("There are more than one correct option");
            }

            //Set the Option and Correct Option for the Question
            question.setOptions(options);
            question.setCorrectOption(correctOption);

            //Save the Question
            Question savedQuestion = questionRepository.save(question);

            //Now Set the Question in Quiz and save this quiz
            quiz.getQuestions().add(savedQuestion);
            quizRepository.save(quiz);

            //Add the savedQuestion into the questionDtoForResponse as Dto
            questionsDtoForResponse.add(mapper.map(savedQuestion, QuestionDto.class));

            }
        }

        return questionsDtoForResponse;
    }


    //Submit Quiz and get score
    public QuizScoreDto submitAndGetScore(QuizSubmissionDto quizSubmissionDto){

        //Get the Quiz by quizId
        String quizId = quizSubmissionDto.getQuizId();
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(
                () -> new ResourceNotFoundException("Quiz not found with the give Id: " + quizId)
        );


        int score=0;
        int totalQuestions = quiz.getQuestions().size();

        for(Map.Entry<String, Integer> entry: quizSubmissionDto.getAnswers().entrySet()){
            String questionId=entry.getKey();
            Integer selectedOptionId=entry.getValue();


            //Get the Current Question
            Question question = quiz.getQuestions().stream()
                    .filter(que -> que.getQuestionId().equals(questionId))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("The Question not Available"));

            //Get the Correct Option for this Question
            Option correctOption = question.getCorrectOption();

            //Update the score if Option is correct
            if(correctOption!=null && ((Integer)correctOption.getOptionId()).equals(selectedOptionId)){
                score++;
            }

        }

        //Create the Object of QuizScoreDto and store the score and number of totalQuestions
        QuizScoreDto quizScoreDto = new QuizScoreDto();
        quizScoreDto.setScore(score);
        quizScoreDto.setTotalQuestions(totalQuestions);

        return quizScoreDto;
    }
}
