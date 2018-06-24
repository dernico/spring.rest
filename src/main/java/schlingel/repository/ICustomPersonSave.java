package schlingel.repository;

public interface ICustomPersonSave<T> {
    <S extends T> S save(S entity);
}
