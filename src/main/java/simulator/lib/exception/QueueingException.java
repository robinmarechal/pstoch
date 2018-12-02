package simulator.lib.exception;

import simulator.lib.exception.enums.QueueingExceptionType;

public class QueueingException extends RuntimeException
{
    private QueueingExceptionType type;

    public QueueingException (String s) {
        this(s, QueueingExceptionType.UNSPECIFIED);
    }

    public QueueingException (String s, QueueingExceptionType type) {
        super(s);
        this.type = type;
    }

    public QueueingExceptionType getType () {
        return type;
    }
}
