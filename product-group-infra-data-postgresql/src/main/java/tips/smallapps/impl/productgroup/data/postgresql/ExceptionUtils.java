package tips.smallapps.impl.productgroup.data.postgresql;

import org.postgresql.util.PSQLException;

class ExceptionUtils {
  private ExceptionUtils() {

  }

  public static boolean isSqlIntegrityConstraintViolationException(Throwable root) {
    do {
      if (isSqlIntegrityException(root)) {
        return true;
      }
      root = root.getCause();
    } while (root != null);
    return false;
  }

  private static boolean isSqlIntegrityException(Throwable throwable) {
    return throwable instanceof PSQLException;
  }

}
