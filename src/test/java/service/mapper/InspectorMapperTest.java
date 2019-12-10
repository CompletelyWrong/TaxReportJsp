package service.mapper;

import data.MockData;
import domain.Inspector;
import entity.user.InspectorEntity;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InspectorMapperTest {
    private static final Inspector INSPECTOR = MockData.MOCK_INSPECTOR;
    private static final InspectorEntity ENTITY = MockData.MOCK_INSPECTOR_ENTITY;

    private final InspectorMapper mapper = new InspectorMapper();

    @Test
    public void mapInspectorToInspectorEntityShouldReturnEntity() {
        final InspectorEntity actual = mapper.mapInspectorToInspectorEntity(INSPECTOR);

        assertThat(actual.getEmail(), is(INSPECTOR.getEmail()));
        assertThat(actual.getPassword(), is(INSPECTOR.getPassword()));
        assertThat(actual.getName(), is(INSPECTOR.getName()));
        assertThat(actual.getSurname(), is(INSPECTOR.getSurname()));
        assertThat(actual.getRole(), is(INSPECTOR.getRole()));
        assertThat(actual.getPatronymic(), is(INSPECTOR.getPatronymic()));
    }

    @Test
    public void mapInspectorEntityToInspectorShouldReturnInspector() {
        final Inspector actual = mapper.mapInspectorEntityToInspector(ENTITY);

        assertThat(actual.getEmail(), is(ENTITY.getEmail()));
        assertThat(actual.getPassword(), is(ENTITY.getPassword()));
        assertThat(actual.getName(), is(ENTITY.getName()));
        assertThat(actual.getSurname(), is(ENTITY.getSurname()));
        assertThat(actual.getRole(), is(ENTITY.getRole()));
        assertThat(actual.getPatronymic(), is(ENTITY.getPatronymic()));
    }
}