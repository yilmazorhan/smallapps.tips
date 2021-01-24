package tr.com.minicrm.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;


@AnalyzeClasses(packages = "tr.com.minicrm.web", importOptions = {AllIgnoredResources.class})
public class ArchitectureConventionTests {

  @ArchTest
  ArchRule CONTROLLERS_SHOULD_BE_SUFFIXED =
      classes().that().areAnnotatedWith(RestController.class).should().haveSimpleNameEndingWith("Controller");

  @ArchTest
  ArchRule NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

  @ArchTest
  ArchRule NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

  @ArchTest
  ArchRule NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

  @ArchTest
  ArchRule NO_CLASSES_SHOULD_USE_FIELD_INJECTION = GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

  @ArchTest
  ArchRule CONFIGURATION_SHOULD_BE_IN_CONFIGIRATION_PACKAGE =
      classes().that().areAnnotatedWith(Configuration.class).should().resideInAPackage("..configuration..");

}
