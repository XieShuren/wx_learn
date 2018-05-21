package wx_learn;

public abstract class MessageHandler<T> {

	protected T message;
	
	public MessageHandler(T message) {
		this.message = message;
	}
	
	public T getMessage() {
		return message;
	}
	
	public abstract Object handle() throws Exception;
}
