package vs.mail.facade.util;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.List;

public final class EmailBuilderUtil {
    @SuppressWarnings("unchecked")
    public static <E> UnmodifiableArrayList<E> getUnmodifiableListForData(List<? extends E> data){
        E[] dataArray = (E[]) data.toArray();
        return new UnmodifiableArrayList<>(dataArray, data.size());
    }
}
