package br.ifpb.edu.testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses(value = { CadastraUmNovoUsuarioTest.class, RealizarLoginSemDadosTest.class, RealizarLoginTest.class,
		CriaUmaTarefaInvalidaTest.class, CriaUmaNovaTarefaTest.class, AtualizaUmaTarefaTest.class,
		FinalizaUmaTarefaTest.class, CriaUmaNovaTarefaTest.class, ExcluiUmaTarefaTest.class })
@RunWith(Suite.class)
public class SuitAllTests {

}
