<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:include href="iri.xslt"/>

<xsl:template match="/ROOT/*" priority="0">
	Unknown(
	<xsl:for-each select=".//*[@id]">
		<xsl:value-of select="."/><xsl:text> </xsl:text>
    </xsl:for-each>)
</xsl:template>

<!-- A entity_A is a [VP] -->
<xsl:template match="/ROOT/S[NP[DT[position() = 1 and @lemma = 'a']][*[@nsubj]] and VP[*[@cop and @lemma='be']]]">
EquivalentClasses(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="VP"/>)
</xsl:template>

<xsl:template match="/ROOT/S[NP[*[@entity]] and VP[*[@cop and @lemma='be']]]">
ClassAssertion(
    <xsl:apply-templates select="VP"/>
    <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[NNP[@entity] and VP[*[@cop and @lemma='be']]]">
ClassAssertion(
    <xsl:apply-templates select="VP"/>
    <xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="NNP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[VP/VBZ/@id=@root and NP[*[@entity]] and VP[NP]]">
ObjectPropertyAssertion(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="VP/NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[VP/VBZ/@id=@root and NNP[@entity] and VP[NNP]]">
ObjectPropertyAssertion(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="NNP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="VP/NNP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[VP/VBZ/@id=@root and NNP[@entity] and VP[NP]]">
ObjectPropertyAssertion(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="NNP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="VP/NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[VP/VBZ/@id=@root and NP[*[@entity]] and VP[NNP]]">
ObjectPropertyAssertion(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="VP/NNP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[NP[DT[position() = 1 and @lemma = 'all']][*[@nsubj]] and VP[VBP[position()=1 and @cop]][NNS]]">
SubClassOf(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/NNS" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[NNS[@nsubj] and VP[VBP[position()=1 and @cop]][NNS]]">
SubClassOf(
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="NNS" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/NNS" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[NN[@lemma = 'nothing'] and VP[MD[@lemma = 'can'] and VP[VB[@lemma = 'be'] and NP[CC[position()=1 and @preconj] and CC[position()=2 and @lemma = 'and']]]]]">
DisjointClasses(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="VP/VP/NP/NP[position()=1]" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="VP/VP/NP/NP[position()=2]" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/SQ[VBZ[@lemma='be']][NP[position()=1]][NP[position()=2]]">
IsDirectSubClassOf(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP[position()=1]" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP[position()=2]" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/SBARQ[SQ/VB/@id=@root and WP and SQ[NP[*[@entity]]]]">
GetObjectPropertyValues(
	<xsl:call-template name="noun_phrase_to_individual_no_annotation">
		<xsl:with-param name="NP" select="SQ/NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="verb_to_object_property_no_annotation">
		<xsl:with-param name="verb" select="SQ/VB" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="VP[VBZ[@cop]][NP[count(SBAR)=0]]">
   <xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
</xsl:template>

<xsl:template match="VP[VBZ[@cop]][NP][count(*)=2]/NP[SBAR[WDT[position() = 1]][S[count(*)=1]/VP[position() = 1]]]">
ObjectIntersectionOf(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="SBAR/S/VP"/>)
</xsl:template>

<xsl:template match="VP[VBZ[@relcl]][NP]">
   ObjectSomeValuesFrom(
    <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VBZ" />
	</xsl:call-template>
   	<xsl:text> </xsl:text>
   	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="VP[VBZ[@relcl]][NP[count(*)=2][QP[position()=1]][NNS]]">
   ObjectMinCardinality(<xsl:value-of select="NP/QP/CD"/><xsl:text> </xsl:text> <xsl:value-of select="VBZ"/><xsl:value-of select="concat(upper-case(substring(NP/NNS/@lemma,1,1)), substring(NP/NNS/@lemma, 2))"/>)
</xsl:template>
 
<xsl:template match="text()|@*"/>

</xsl:stylesheet>