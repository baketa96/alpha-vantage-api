package com.example.alphavantageapi.telegrambot;

public enum BotCommandsEnum {
    START("/start", "Start the bot"),
    HELP("/help", "Get help information"),
    MOST_ACTIVE(
            "/mostactive",
            "Get top 20 gainers, losers, and the most active traded tickers in the US market."),
    SEARCH(
            "/searchentity",
            "Get best best-matching symbols and market information based on provided keyword."),
    LATEST_INFO("/latestinfo", "Get latest info for entity."),
    MARKETS_STATUS("/marketsstatus", "Get supported markets status.");
    ;

    private final String command;
    private final String description;

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    BotCommandsEnum(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public static String getAllCommands() {
        StringBuilder commands = new StringBuilder();
        for (BotCommandsEnum cmd : values()) {
            commands.append(cmd.getCommand())
                    .append(" - ")
                    .append(cmd.getDescription())
                    .append("\n");
        }
        return commands.toString();
    }
}
