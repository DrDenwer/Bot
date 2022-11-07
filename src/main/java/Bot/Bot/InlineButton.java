package Bot.Bot;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class InlineButton extends TelegramLongPollingBot {


    private String username = /*YOUR BOT USERNAME */;

    private String token = /*YOUR BOT TOKEN*/;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {



            long idchat = /* YOUR CHANNEL ID */;

            // Create a message object
            SendMessage message = new SendMessage();
            message.setChatId(idchat);
            message.setParseMode(ParseMode.MARKDOWN);

            //Create inline button

            Long user = update.getMessage().getChatId();
            String us = "tg://user?id="+((user));
            String message_text = update.getMessage().getText();
            message.setText(message_text);

            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();

            InlineKeyboardButton button = new InlineKeyboardButton();

            button.setText("More Details \uD83D\uDE4C\uD83C\uDFFB");
            button.setUrl(us);
            rowInline.add(button);

            // Set the keyboard to the markup

            rowsInline.add(rowInline);

            // Add it to the message

            markupInline.setKeyboard(rowsInline);
            message.setReplyMarkup(markupInline);


            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }




        }

    }

}
