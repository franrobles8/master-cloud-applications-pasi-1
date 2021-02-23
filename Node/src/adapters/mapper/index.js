const mapToDomain = function mapToDomain(document, DomainModel) {
    return new DomainModel(document);
};

module.exports = { mapToDomain };