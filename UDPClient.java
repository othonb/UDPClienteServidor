/*
 * UDPClient.java
 *
 * Created on 20 de Maio de 2005, 16:14
 */

//package sockets;

import java.io.*;
import java.net.*;

/**
 *
 * @author  Othon Batista
 */
class UDPClient 
{

public static void main(String[] args)
{
    // Instancia e cria um objeto para ler informações do usuário
    BufferedReader doUsuario= new BufferedReader(new InputStreamReader(System.in)); 

    try {
        // Cria um socket UDP  
        DatagramSocket socketCliente = new DatagramSocket(); 

        // Recupera o endereço IP do servidor (que neste exemplo está em localhost)
        InetAddress enderecoIp = InetAddress.getByName("localhost"); 
  
        // Cria dois vetores de byte, um para enviar e outro para receber
        byte[] dadosEnviados; 
        byte[] dadosRecebidos = new byte [1024];

        // Lê uma frase do usuário
        System.out.print ("Informe uma frase para ser passada para maiúscula: "); 
        String frase = doUsuario.readLine(); 
 
        // Recupera o vetor de bytes da frase digitada
        dadosEnviados = frase.getBytes();

        // Cria um datagrama UDP para enviar ao servidor na porta 6789
        DatagramPacket datagramaEnviado = new DatagramPacket(dadosEnviados, 
                                                        dadosEnviados.length, 
                                                        enderecoIp, 
                                                        6789); 
  
        // Envia o datagrama através do socket UDP criado
        socketCliente.send(datagramaEnviado); 

        // Prepara um datagrama UDP para receber as informações do servidor
        DatagramPacket datagramaRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length); 
  
        // Recebe o datagrama do servidor
        socketCliente.receive(datagramaRecebido); 
  
        // Pega os dados do datagrama UDP como um vetor de bytes e cria uma String
        String fraseModificada = new String(datagramaRecebido.getData()); 
  
        // Exibe a frase modificada no servidor
        System.out.println("Do Servidor:" + fraseModificada); 

        // Fecha o socket
        socketCliente.close();

    } catch (IOException e) {
        
        System.out.println ("Erro de socket: " + e);
    
    }
     
}

}
