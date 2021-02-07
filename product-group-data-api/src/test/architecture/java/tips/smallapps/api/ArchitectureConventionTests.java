package tips.smallapps.api;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;


@AnalyzeClasses(packages = "tips.smallapps")
public class ArchitectureConventionTests {

  @ArchTest
  ArchRule EXCEPTIONS_SHOULD_BE_IN_EXCEPTION_PACKAGE =
      classes().that().areAssignableTo(RuntimeException.class).should().resideInAPackage("..exceptions..");

  @ArchTest
  ArchRule CLASSES_IN_SERVICE_PACKAGE_SHOULD_END_WITH_DATA_SERVICE =
      classes().that().resideInAPackage("..service..").should().haveSimpleNameEndingWith("DataService");
  
  @ArchTest
  ArchRule NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
  
  @ArchTest
  ArchRule NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
  
  @ArchTest
  ArchRule NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

}
