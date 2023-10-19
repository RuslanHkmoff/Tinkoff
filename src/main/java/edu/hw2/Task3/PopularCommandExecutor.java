package edu.hw2.Task3;

public class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                throw e;
            } catch (Exception e) {
                if (attempt == maxAttempts) {
                    throw new ConnectionException("Unable to execute command, number of attempts exceeded", e);
                }
            }
        }
    }

}
