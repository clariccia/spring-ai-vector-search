package org.vectorsearch.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromptRepository {

    private final ChatModel chatModel;

    public ChatResponse prompt(String question) {
        return chatModel.call(new Prompt(question));
    }

}
