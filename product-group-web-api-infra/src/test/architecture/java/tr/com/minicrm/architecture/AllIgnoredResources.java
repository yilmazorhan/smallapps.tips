package tr.com.minicrm.architecture;

import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableSet;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;

final class AllIgnoredResources implements ImportOption {
  private static final Pattern MAVEN_PATTERN = Pattern.compile(".*/target/test-classes/.*");
  private static final Pattern GRADLE_PATTERN = Pattern.compile(".*/build/classes/([^/]+/)?test/.*");
  private static final Pattern INTELLIJ_PATTERN = Pattern.compile(".*/out/test/classes/.*");
  private static final Pattern ECLIPSE_PATTERN = Pattern.compile(".*/bin/test/.*");
  private static final Pattern GENERATED_PATTERN = Pattern.compile(".*/generated/.*");

  private static final Set<Pattern> EXCLUDED_PATTERN =
      ImmutableSet.of(MAVEN_PATTERN, GRADLE_PATTERN, INTELLIJ_PATTERN, ECLIPSE_PATTERN, GENERATED_PATTERN);

  @Override
  public boolean includes(Location location) {
    for (Pattern pattern : EXCLUDED_PATTERN) {
      if (location.matches(pattern)) {
        return false;
      }
    }
    return true;
  }
}