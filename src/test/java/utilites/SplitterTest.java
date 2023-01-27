package utilites;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author chengsukai
 * @since 2022-09-22 12:23
 */
public class SplitterTest {

    @Test
    public void testSplitOnSplit(){
        List<String> result = Splitter.on("|").splitToList("hello|world");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));
    }

    @Test
    public void testSplit_OnSplit_OmitEmpty(){
        List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("hello|world|||");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(2));
    }

    @Test
    public void testSplit_on_Split_OmitEmpty_TrimResult(){
        List<String> result = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("hello | world|||");
        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));

    }
}
