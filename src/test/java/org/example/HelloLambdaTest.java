package org.example;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HelloLambdaTest
{

    HelloLambda hl;

    @Mock
    Context  context;

    @Mock
    LambdaLogger logger;

    @BeforeEach
    public void setup(){
        Mockito.when(context.getLogger()).thenReturn(logger);

        doAnswer(call -> {
            System.out.println((String) call.getArgument(0));
            return null;
        }).when(logger).log(anyString());

        hl = new HelloLambda();
    }

    @Test
    public void testHandleRequest(){
        when(context.getFunctionName()).thenReturn("handleRequest");
        assertEquals("ANKIT", hl.handleRequest("ankit", context));
    }
}
