package client;

public class P2PServer implements Runnable {
	// Recieving the file
	private String fileName
	private long fileSize;
	
	public P2PServer(String fileName, long fileSize) {
		this.fileName = fileName
		this.fileSize = fileSize;
		new Thread(this).start();
	}
	
	public void run() {
		try {
			start();
		} catch (IOException e) {
			System.out.println("The port 4900 is already in use.");
		}
	}
	
	public void start() throws IOException {
		ServerSocket serverSocket = new ServerSocket(4900);
		Socket socket = serverSocket.accept();
		InputStream inStream = socket.getInputStream();
		FileOutputStream fos = new FileOutputStream("new" + fileName);
		// Read 128 bytes, then write them to file, then read until so many bytes are read
		int maxBytes = 0;
		byte[] buffer = new byte[128];
		while (maxBytes < fileSize) {
			int numRead = inStream.read(buffer);
			maxBytes += numRead;
			fos.write(buffer, 0, numRead);
		}
		inStream.close();
		fos.close();
		socket.close();
		serverSocket.close();
	}
}