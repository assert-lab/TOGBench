package org.springside.modules.utils.reflect;

import static org.assertj.core.api.Assertions.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.Test;

public class ClassUtilTest_OE25Dev {

	public void classPresent(){
		assertThat(ClassUtil.isPresent("a.b.c", ClassUtil.getDefaultClassLoader())).isFalse();
		assertThat(ClassUtil.isPresent("org.springside.modules.utils.reflect.ClassUtil", ClassUtil.getDefaultClassLoader())).isTrue();
	}
	
	public static class ParentBean<T, ID> {
	}

	public static class TestBean extends ParentBean<String, Long> {

	}

	public static class TestBean2 extends ParentBean {
	}

	public static class TestBean3 {

	}

	public interface AInterface {
	}

	@CAnnotation
	public interface BInterface extends AInterface {
		@FAnnotation
		void hello();
	}

	public interface CInterface {
	}

	public interface DInterface {
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface AAnnotation {
	}

	@Retention(RetentionPolicy.RUNTIME)
	@AAnnotation
	public @interface BAnnotation {
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface CAnnotation {
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface DAnnotation {
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface EAnnotation {
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface FAnnotation {
	}

	@DAnnotation
	public static class AClass implements DInterface {

		@EAnnotation
		private int afield;

		private int cfield;

		@FAnnotation
		private int dfield;

		@AAnnotation
		public int tfield;

		@AAnnotation
		protected int vfield;

		// not counted as public annotated method
		public void hello2(int i) {

		}

		// counted as public annotated method
		@FAnnotation
		public void hello4(int i) {

		}

		// not counted as public annotated method
		@FAnnotation
		protected void hello5(int i) {

		}

		// not counted as public annotated method
		@FAnnotation
		private void hello6(int i) {

		}

		// not counted as public annotated method, because the child override it
		@FAnnotation
		public void hello7(int i) {

		}

	}

	@BAnnotation
	public static class BClass extends AClass implements CInterface, BInterface {

		@EAnnotation
		private int bfield;

		@EAnnotation
		private int efield;

		@AAnnotation
		public int sfield;

		@AAnnotation
		protected int ufield;

		// counted as public annotated method, BInterface
		@Override
		@EAnnotation
		public void hello() {
			// TODO Auto-generated method stub

		}

		public void hello2(int i) {

		}

		// counted as public annotated method
		@FAnnotation
		public void hello3(int i) {

		}

		// not counted as public annotated method
		@Override
		public void hello7(int i) {

		}

	}

	@Test
	public void getMessage_1_oe() {
		assertThat(ClassUtil.getShortClassName(ClassUtilTest.class)).isEqualTo("ClassUtilTest");
	}

	@Test
	public void getMessage_3_oe() {

		assertThat(ClassUtil.getShortClassName(ClassUtilTest.class.getName())).isEqualTo("ClassUtilTest");
	}

	@Test
	public void getMessage_5_oe() {


		assertThat(ClassUtil.getPackageName(ClassUtilTest.class)).isEqualTo("org.springside.modules.utils.reflect");
	}

	@Test
	public void getMessage_6_oe() {


		assertThat(ClassUtil.getPackageName(BClass.class)).isEqualTo("org.springside.modules.utils.reflect");
	}

	@Test
	public void getMessage_7_oe() {


		assertThat(ClassUtil.getPackageName(ClassUtilTest.class.getName())).isEqualTo("org.springside.modules.utils.reflect");
	}

	@Test
	public void getMessage_8_oe() {


		assertThat(ClassUtil.getPackageName(BClass.class.getName())).isEqualTo("org.springside.modules.utils.reflect");
	}

	@Test
	public void getAllClass_1_oe() {

		assertThat(ClassUtil.getAllInterfaces(BClass.class)).hasSize(4).contains(AInterface.class,BInterface.class,CInterface.class,DInterface.class);
	}

	@Test
	public void getAllClass_2_oe() {


		assertThat(ClassUtil.getAllSuperclasses(BClass.class)).hasSize(2).contains(AClass.class, Object.class);
	}

	@Test
	public void getAllClass_3_oe() {



		assertThat(ClassUtil.getAllAnnotations(BClass.class)).hasSize(4);
	}

	@Test
	public void getAllClass_4_oe() {




		assertThat(ClassUtil.getAnnotatedPublicFields(BClass.class,AAnnotation.class)).hasSize(2).contains(ClassUtil.getAccessibleField(BClass.class,"sfield"),ClassUtil.getAccessibleField(BClass.class,"tfield"));
	}

	@Test
	public void getAllClass_5_oe() {





		assertThat(ClassUtil.getAnnotatedFields(BClass.class,EAnnotation.class)).hasSize(3).contains(ClassUtil.getAccessibleField(BClass.class,"bfield"),ClassUtil.getAccessibleField(BClass.class,"efield"),ClassUtil.getAccessibleField(AClass.class,"afield"));
	}

	@Test
	public void getAllClass_6_oe() {






		assertThat(ClassUtil.getAnnotatedFields(BClass.class,FAnnotation.class)).hasSize(1).contains(ClassUtil.getAccessibleField(AClass.class,"dfield"));
	}

	@Test
	public void getAllClass_7_oe() {







		assertThat(ClassUtil.getAnnotatedPublicMethods(BClass.class,FAnnotation.class)).hasSize(3).contains(ClassUtil.getAccessibleMethodByName(BClass.class,"hello"),ClassUtil.getAccessibleMethodByName(BClass.class,"hello3"),ClassUtil.getAccessibleMethodByName(AClass.class,"hello4"));
	}

	@Test
	public void getSuperClassGenricType_1_oe() {
		assertThat(ClassUtil.getClassGenricType(TestBean.class)).isEqualTo(String.class);
	}

	@Test
	public void getSuperClassGenricType_2_oe() {
		assertThat(ClassUtil.getClassGenricType(TestBean.class, 1)).isEqualTo(Long.class);
	}

	@Test
	public void getSuperClassGenricType_3_oe() {

		assertThat(ClassUtil.getClassGenricType(TestBean2.class)).isEqualTo(Object.class);
	}

	@Test
	public void getSuperClassGenricType_4_oe() {


		assertThat(ClassUtil.getClassGenricType(TestBean3.class)).isEqualTo(Object.class);
	}

}
