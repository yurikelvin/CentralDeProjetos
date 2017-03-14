package projetos.testes;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import projetos.ProjetoController;

public class ProjetoControllerTest {


	private ProjetoController projetoController;
	
	@Before
	public void setUP(){
		projetoController = new ProjetoController();
	}
	
	@Test
	public void testAdicionaMonitoria() {
		
		//Adicionando Monitoria com o nome Nulo
			try {
				projetoController.adicionaMonitoria(null, "Calculo", 20, "Ajudar os Alunos", "Segundo", "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Nome de monitoria nulo");
			} catch (Exception e) {
				Assert.assertEquals("Nome nulo ou vazio", e.getMessage());
			}
		
		//Adicionando Monitoria com  o Nome Vazio
			try {
				projetoController.adicionaMonitoria("", "Calculo", 20, "Ajudar os Alunos", "Segundo", "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Nome de monitoria Vazio");				
				} catch (Exception e) {
					Assert.assertEquals("Nome nulo ou vazio", e.getMessage());
				}
		
			
		//Adicionando Monitoria com apenas o Disciplina Nula
			try {
				projetoController.adicionaMonitoria("Monitoria Calculo 2", null, 20, "Ajudar os Alunos", "Segundo", "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Disciplina de monitoria nulo");
			} catch (Exception e) {
				Assert.assertEquals("Disciplina nulo ou vazio", e.getMessage());
			}
			
			
		//Adicionando Monitoria com apenas o Disciplina Vazio
			try {
				projetoController.adicionaMonitoria("Monitoria Calculo 2", "", 20, "Ajudar os Alunos", "Segundo", "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Disciplina de monitoria Vazio");
			} catch (Exception e) {
				Assert.assertEquals("Disciplina nulo ou vazio", e.getMessage());
			}
			
		//Adicionando Monitoria com apenas o Rendimento Negativo
			try {
				projetoController.adicionaMonitoria("Monitoria Calculo 2", "Calculo", -5, "Ajudar os Alunos", "Segundo", "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Rendimento de monitoria negativo");
			} catch (Exception e) {
				Assert.assertEquals("Rendimento invalido", e.getMessage());
			}
			
		//Adicionando Monitoria com apenas o Objetivo nulo
			try {
				projetoController.adicionaMonitoria("Monitoria Calculo 2", "Calculo", 5, null, "Segundo", "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Objetivo de monitoria Nulo");
			} catch (Exception e) {
				Assert.assertEquals("Objetivo nulo ou vazio", e.getMessage());
			}
			
		//Adicionando Monitoria com apenas o Objetivo Vazio
			try {
				projetoController.adicionaMonitoria("Monitoria Calculo 2", "Calculo", 5, "", "Segundo", "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Objetivo de monitoria Vazio");
			} catch (Exception e) {
				Assert.assertEquals("Objetivo nulo ou vazio", e.getMessage());
			}
			
		//Adicionando Monitoria com apenas o Perido nulo
			try {
				projetoController.adicionaMonitoria("Monitoria Calculo 2", "Calculo", 5, "Ajudar os alunos", null, "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Periodo de monitoria Nulo");
			} catch (Exception e) {
				Assert.assertEquals("Periodo nulo ou vazio", e.getMessage());
			}
			
		//Adicionando Monitoria com apenas o Perido Vazio
			try {
				projetoController.adicionaMonitoria("Monitoria Calculo 2", "Calculo", 5, "Ajudar os alunos", "", "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Periodo de monitoria Vazio");
			} catch (Exception e) {
				Assert.assertEquals("Periodo nulo ou vazio", e.getMessage());
			}
			
		//Adicionando Monitoria com apenas a Data fora da formatacao
			try {
				projetoController.adicionaMonitoria("Monitoria Calculo 2", "Calculo", 5, "Ajudar os alunos", "Segundo", "302/2017", 5);
				Assert.fail("Lancamento de Exception com a Data fora da formatacao");
			} catch (Exception e) {
				Assert.assertEquals("Data invalida.", e.getMessage());
			}
			
		//Adicionando Monitoria com apenas a Data Nula
			try {
				projetoController.adicionaMonitoria("Monitoria Calculo 2", "Calculo", 5, "Ajudar os alunos", "Segundo", null, 5);
				Assert.fail("Lancamento de Exception com a Data Nula");
			} catch (Exception e) {
				Assert.assertEquals("Data nula ou vazia", e.getMessage());
			}
			
		//Adicionando Monitoria com apenas a Data Vazia
			try {
				projetoController.adicionaMonitoria("Monitoria Calculo 2", "Calculo", 5, "Ajudar os alunos", "Segundo", "", 5);
				Assert.fail("Lancamento de Exception com a Data Vazia");
			} catch (Exception e) {
				Assert.assertEquals("Data nula ou vazia", e.getMessage());
			}
			
		//Adicionando Monitoria com apenas a duracao negativa
			try {
				projetoController.adicionaMonitoria("Monitoria Calculo 2", "Calculo", 5, "Ajudar os alunos", "Segundo", "25/02/2017", -5);
				Assert.fail("Lancamento de Exception com a duracao negativa");
			} catch (Exception e) {
				Assert.assertEquals("Duracao Invalida", e.getMessage());
			}
			
		//Adicionando Monitoria com apenas a duracao igual a zero
			try {
				projetoController.adicionaMonitoria("Monitoria Calculo 2", "Calculo", 5, "Ajudar os alunos", "Segundo", "25/02/2017", 0);
				Assert.fail("Lancamento de Exception com a duracao igual a 0");
			} catch (Exception e) {
				Assert.assertEquals("Duracao Invalida", e.getMessage());
			}
			
		
			
		
	
		
	}

	@Test
	public void testAdicionaPET() {
		
		//Adicionando PET com o nome Nulo
			try {
				projetoController.adicionaPET(null, "objetivo muito legal", 2, 20, 2,6,3, "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Nome de PET nulo");
			} catch (Exception e) {
				Assert.assertEquals("Nome nulo ou vazio", e.getMessage());
		}
	
		//Adicionando PET com o nome Vazio
			try {
				projetoController.adicionaPET("", "objetivo mutio legal", 2, 20, 2,6,3, "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Nome de PET vazio");
				} catch (Exception e) {
					Assert.assertEquals("Nome nulo ou vazio", e.getMessage());
				}
		
		//Adicionando PET com o OBjetivo nulo
			try {
				projetoController.adicionaPET("Birl", null, 2, 20, 2,6,3, "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Objetivo de PET nulo");
					} catch (Exception e) {
					Assert.assertEquals("Objetivo nulo ou vazio", e.getMessage());
						}
			
		//Adicionando PET com o OBjetivo vazio
			try {
				projetoController.adicionaPET("Birl", "", 2, 20, 2,6,3, "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Objetivo de PET vazio");
					} catch (Exception e) {
					Assert.assertEquals("Objetivo nulo ou vazio", e.getMessage());
						}
			
		//Adicionando PET com o Impacto negativo
			try {
				projetoController.adicionaPET("Birl", "objetivo legal", -2, 20, 2,6,3, "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Impacto de PET negativo");
					} catch (Exception e) {
					Assert.assertEquals("Impacto invalido", e.getMessage());
						}
			
		//Adicionando PET com o Rendimento negativo
			try {
				projetoController.adicionaPET("Birl", "objetivo legal", 2, -20, 2,6,3, "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com Rendimento de PET negativo");
					} catch (Exception e) {
					Assert.assertEquals("Rendimento invalido", e.getMessage());
						}
			
		//Adicionando PET com a Producao tecnica negativo
			try {
				projetoController.adicionaPET("Birl", "objetivo legal", 2, 20, -2,6,3, "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com a producao tecnica de PET negativo");
					} catch (Exception e) {
					Assert.assertEquals("Numero de producoes tecnicas invalido", e.getMessage());
						}
			
		//Adicionando PET com a Producao academica negativo
			try {
				projetoController.adicionaPET("Birl", "objetivo legal", 2, 20, 2, -6, 3, "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com a producao academica de PET negativo");
					} catch (Exception e) {
					Assert.assertEquals("Numero de producoes academicas invalido", e.getMessage());
						}
			
		//Adicionando PET com a Producao academica negativo
			try {
				projetoController.adicionaPET("Birl", "objetivo legal", 2, 20, 2, 6, -3, "25/02/2017", 5);
				Assert.fail("Lancamento de Exception com patentes de PET negativo");
					} catch (Exception e) {
					Assert.assertEquals("Numero de patentes invalido", e.getMessage());
						}
			
		//Adicionando PET com a Data na formatacao errada
			try {
				projetoController.adicionaPET("Birl", "objetivo legal", 2, 20, 2, 6, 3, "2502/2017", 5);
				Assert.fail("Lancamento de Exception a Data de PET na formatacao errada");
					} catch (Exception e) {
					Assert.assertEquals("Data invalida.", e.getMessage());
						}
			
		//Adicionando PET com a Data nula
			try {
				projetoController.adicionaPET("Birl", "objetivo legal", 2, 20, 2, 6, 3, null, 5);
				Assert.fail("Lancamento de Exception a Data de PET nula");
					} catch (Exception e) {
					Assert.assertEquals("Data nula ou vazia", e.getMessage());
						}
			
		//Adicionando PET com a Data vazia
			try {
				projetoController.adicionaPET("Birl", "objetivo legal", 2, 20, 2, 6, 3, "", 5);
				Assert.fail("Lancamento de Exception a Data de PET vazia");
					} catch (Exception e) {
					Assert.assertEquals("Data nula ou vazia", e.getMessage());
						}
			
		//Adicionando PET com a duracao igual a zero
			try {
				projetoController.adicionaPET("Birl", "objetivo legal", 2, 20, 2, 6, 3, "25/02/2017", 0);
				Assert.fail("Lancamento de Exception de duracao de PET igual a zero");
					} catch (Exception e) {
					Assert.assertEquals("Duracao invalida", e.getMessage());
						}
			
		//Adicionando PET com a duracao negativa
			try {
				projetoController.adicionaPET("Birl", "objetivo legal", 2, 20, 2, 6, 3, "25/02/2017", -5);
				Assert.fail("Lancamento de Exception de duracao de PET negativa");
					} catch (Exception e) {
					Assert.assertEquals("Duracao invalida", e.getMessage());
						}
		
	
	}

	/*@Test
	public void testAdicionaExtensao() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdicionaPED() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInfoProjeto() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProjetos() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditaProjeto() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveProjeto() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCodigoProjeto() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveParticipacao() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}*/

}
